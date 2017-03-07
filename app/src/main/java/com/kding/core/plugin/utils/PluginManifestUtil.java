//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding.core.plugin.utils;

import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.util.Log;

import com.kding.core.plugin.PluginManager;
import com.kding.core.plugin.environment.PlugInfo;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class PluginManifestUtil {
    public PluginManifestUtil() {
    }

    public static void setManifestInfo(Context context, String apkPath, PlugInfo info) throws XmlPullParserException, IOException {
        ZipFile zipFile = new ZipFile(new File(apkPath), 1);
        ZipEntry manifestXmlEntry = zipFile.getEntry("AndroidManifest.xml");
        String manifestXML = XmlManifestReader.getManifestXMLFromAPK(zipFile, manifestXmlEntry);
        PackageInfo pkgInfo = context.getPackageManager().getPackageArchiveInfo(apkPath, 1167);
        if (pkgInfo != null && pkgInfo.activities != null) {
            pkgInfo.applicationInfo.publicSourceDir = apkPath;
            pkgInfo.applicationInfo.sourceDir = apkPath;
            File libDir = PluginManager.getSingleton().getPluginLibPath(info);

            try {
                if (extractLibFile(context, zipFile, libDir) && VERSION.SDK_INT >= 9) {
                    pkgInfo.applicationInfo.nativeLibraryDir = libDir.getAbsolutePath();
                }
            } finally {
                zipFile.close();
            }

            info.setPackageInfo(pkgInfo);
            setAttrs(info, manifestXML);
        } else {
            throw new XmlPullParserException("No any activity in " + apkPath);
        }
    }

    private static boolean extractLibFile(Context context, ZipFile zip, File tardir) throws IOException {
        if (!tardir.exists()) {
            tardir.mkdirs();
        }

        String defaultArch = "armeabi";
        ApplicationInfo ai = context.getApplicationInfo();
        String hostAppArch = ai.nativeLibraryDir;
        defaultArch = hostAppArch.substring(hostAppArch.lastIndexOf(47) + 1);
        if (defaultArch.startsWith("mips")) {
            defaultArch = "mips";
        } else if (defaultArch.startsWith("x86")) {
            defaultArch = "x86";
        } else if (defaultArch.startsWith("arm64")) {
            defaultArch = "arm64";
        } else {
            defaultArch = "armeabi";
        }

        Log.e("=======", defaultArch);
        HashMap archLibEntries = new HashMap();
        Enumeration arch = zip.entries();

        String ename;
        while (arch.hasMoreElements()) {
            ZipEntry libEntries = (ZipEntry) arch.nextElement();
            String hasLib = libEntries.getName();
            if (hasLib.startsWith("/")) {
                hasLib = hasLib.substring(1);
            }

            if (hasLib.startsWith("lib/") && !libEntries.isDirectory()) {
                int sp = hasLib.indexOf(47, 4);
                String libEntry;
                if (sp > 0) {
                    ename = hasLib.substring(4, sp);
                    libEntry = ename.toLowerCase();
                } else {
                    libEntry = defaultArch;
                }

                Object ename1 = (List) archLibEntries.get(libEntry);
                if (ename1 == null) {
                    ename1 = new LinkedList();
                    archLibEntries.put(libEntry, ename1);
                }

                ((List) ename1).add(libEntries);
            }
        }

        String arch1 = System.getProperty("os.arch");
        List libEntries1 = (List) archLibEntries.get(arch1.toLowerCase());
        if (libEntries1 == null) {
            libEntries1 = (List) archLibEntries.get(defaultArch);
        }

        boolean hasLib1 = false;
        if (libEntries1 != null) {
            hasLib1 = true;
            if (!tardir.exists()) {
                tardir.mkdirs();
            }

            Iterator sp1 = libEntries1.iterator();

            while (sp1.hasNext()) {
                ZipEntry libEntry1 = (ZipEntry) sp1.next();
                ename = libEntry1.getName();
                String pureName = ename.substring(ename.lastIndexOf(47) + 1);
                File target = new File(tardir, pureName);
                FileUtil.writeToFile(zip.getInputStream(libEntry1), target);
            }
        }

        return hasLib1;
    }

    private static void setAttrs(PlugInfo info, String manifestXML) throws XmlPullParserException, IOException {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser parser = factory.newPullParser();
        parser.setInput(new StringReader(manifestXML));
        int eventType = parser.getEventType();
        String namespaceAndroid = null;

        do {
            switch (eventType) {
                case 0:
                case 1:
                case 3:
                default:
                    break;
                case 2:
                    String tag = parser.getName();
                    if (tag.equals("manifest")) {
                        namespaceAndroid = parser.getNamespace("android");
                    } else if ("activity".equals(parser.getName())) {
                        addActivity(info, namespaceAndroid, parser);
                    } else if ("receiver".equals(parser.getName())) {
                        addReceiver(info, namespaceAndroid, parser);
                    } else if ("service".equals(parser.getName())) {
                        addService(info, namespaceAndroid, parser);
                    } else if ("application".equals(parser.getName())) {
                        parseApplicationInfo(info, namespaceAndroid, parser);
                    }
            }

            eventType = parser.next();
        } while (eventType != 1);

    }

    private static void parseApplicationInfo(PlugInfo info, String namespace, XmlPullParser parser) throws XmlPullParserException, IOException {
        String applicationName = parser.getAttributeValue(namespace, "name");
        String packageName = info.getPackageInfo().packageName;
        ApplicationInfo applicationInfo = info.getPackageInfo().applicationInfo;
        applicationInfo.name = getName(applicationName, packageName);
    }

    private static void addActivity(PlugInfo info, String namespace, XmlPullParser parser) throws XmlPullParserException, IOException {
        int eventType = parser.getEventType();
        String activityName = parser.getAttributeValue(namespace, "name");
        String packageName = info.getPackageInfo().packageName;
        activityName = getName(activityName, packageName);
        ResolveInfo act = new ResolveInfo();
        act.activityInfo = info.findActivityByClassNameFromPkg(activityName);

        do {
            switch (eventType) {
                case 2:
                    String tag = parser.getName();
                    if ("intent-filter".equals(tag)) {
                        if (act.filter == null) {
                            act.filter = new IntentFilter();
                        }
                    } else {
                        String category;
                        if ("action".equals(tag)) {
                            category = parser.getAttributeValue(namespace, "name");
                            act.filter.addAction(category);
                        } else if ("category".equals(tag)) {
                            category = parser.getAttributeValue(namespace, "name");
                            act.filter.addCategory(category);
                        } else if ("data".equals(tag)) {
                            ;
                        }
                    }
            }

            eventType = parser.next();
        } while (!"activity".equals(parser.getName()));

        info.addActivity(act);
    }

    private static void addService(PlugInfo info, String namespace, XmlPullParser parser) throws XmlPullParserException, IOException {
        int eventType = parser.getEventType();
        String serviceName = parser.getAttributeValue(namespace, "name");
        String packageName = info.getPackageInfo().packageName;
        serviceName = getName(serviceName, packageName);
        ResolveInfo service = new ResolveInfo();
        service.serviceInfo = info.findServiceByClassName(serviceName);

        do {
            switch (eventType) {
                case 2:
                    String tag = parser.getName();
                    if ("intent-filter".equals(tag)) {
                        if (service.filter == null) {
                            service.filter = new IntentFilter();
                        }
                    } else {
                        String category;
                        if ("action".equals(tag)) {
                            category = parser.getAttributeValue(namespace, "name");
                            service.filter.addAction(category);
                        } else if ("category".equals(tag)) {
                            category = parser.getAttributeValue(namespace, "name");
                            service.filter.addCategory(category);
                        } else if ("data".equals(tag)) {
                            ;
                        }
                    }
            }

            eventType = parser.next();
        } while (!"service".equals(parser.getName()));

        info.addService(service);
    }

    private static void addReceiver(PlugInfo info, String namespace, XmlPullParser parser) throws XmlPullParserException, IOException {
        int eventType = parser.getEventType();
        String receiverName = parser.getAttributeValue(namespace, "name");
        String packageName = info.getPackageInfo().packageName;
        receiverName = getName(receiverName, packageName);
        ResolveInfo receiver = new ResolveInfo();
        receiver.activityInfo = info.findReceiverByClassName(receiverName);

        do {
            switch (eventType) {
                case 2:
                    String tag = parser.getName();
                    if ("intent-filter".equals(tag)) {
                        if (receiver.filter == null) {
                            receiver.filter = new IntentFilter();
                        }
                    } else {
                        String category;
                        if ("action".equals(tag)) {
                            category = parser.getAttributeValue(namespace, "name");
                            receiver.filter.addAction(category);
                        } else if ("category".equals(tag)) {
                            category = parser.getAttributeValue(namespace, "name");
                            receiver.filter.addCategory(category);
                        } else if ("data".equals(tag)) {
                            ;
                        }
                    }
            }

            eventType = parser.next();
        } while (!"receiver".equals(parser.getName()));

        info.addReceiver(receiver);
    }

    private static String getName(String nameOrig, String pkgName) {
        if (nameOrig == null) {
            return null;
        } else {
            StringBuilder sb;
            if (nameOrig.startsWith(".")) {
                sb = new StringBuilder();
                sb.append(pkgName);
                sb.append(nameOrig);
            } else {
                if (nameOrig.contains(".")) {
                    return nameOrig;
                }

                sb = new StringBuilder();
                sb.append(pkgName);
                sb.append('.');
                sb.append(nameOrig);
            }

            return sb.toString();
        }
    }
}
