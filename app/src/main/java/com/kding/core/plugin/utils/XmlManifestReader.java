//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding.core.plugin.utils;

import com.kding.core.plugin.utils.XmlResourceParser;
import java.io.File;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

class XmlManifestReader {
    public static final String DEFAULT_XML = "AndroidManifest.xml";
    private static final float[] RADIX_MULTS = new float[]{0.00390625F, 3.051758E-5F, 1.192093E-7F, 4.656613E-10F};
    private static final String[] DIMENSION_UNITS = new String[]{"px", "dip", "sp", "pt", "in", "mm", "", ""};
    private static final String[] FRACTION_UNITS = new String[]{"%", "%p", "", "", "", "", "", ""};

    private XmlManifestReader() {
    }

    public static String getManifestXMLFromAPK(String apkPath) {
        ZipFile file = null;
        String rs = null;

        try {
            File e = new File(apkPath);
            file = new ZipFile(e, 1);
            ZipEntry entry = file.getEntry("AndroidManifest.xml");
            rs = getManifestXMLFromAPK(file, entry);
        } catch (Exception var13) {
            var13.printStackTrace();
        } finally {
            if(file != null) {
                try {
                    file.close();
                } catch (IOException var12) {
                    var12.printStackTrace();
                }
            }

        }

        return rs;
    }

    public static String getManifestXMLFromAPK(ZipFile file, ZipEntry entry) {
        StringBuilder xmlSb = new StringBuilder(100);
        XmlResourceParser parser = null;

        try {
            parser = new XmlResourceParser();
            parser.open(file.getInputStream(entry));
            StringBuilder e = new StringBuilder(10);
            String indentStep = "\t";

            while(true) {
                int type;
                while((type = parser.next()) != 1) {
                    switch(type) {
                        case 0:
                            log(xmlSb, "<?xml version=\"1.0\" encoding=\"utf-8\"?>", new Object[0]);
                        case 1:
                        default:
                            break;
                        case 2:
                            log(false, xmlSb, "%s<%s%s", new Object[]{e, getNamespacePrefix(parser.getPrefix()), parser.getName()});
                            e.append("\t");
                            int namespaceCountBefore = parser.getNamespaceCount(parser.getDepth() - 1);
                            int namespaceCount = parser.getNamespaceCount(parser.getDepth());

                            int i;
                            for(i = namespaceCountBefore; i != namespaceCount; ++i) {
                                log(xmlSb, "%sxmlns:%s=\"%s\"", new Object[]{i == namespaceCountBefore?"  ":e, parser.getNamespacePrefix(i), parser.getNamespaceUri(i)});
                            }

                            i = 0;

                            for(int size = parser.getAttributeCount(); i != size; ++i) {
                                log(false, xmlSb, "%s%s%s=\"%s\"", new Object[]{" ", getNamespacePrefix(parser.getAttributePrefix(i)), parser.getAttributeName(i), getAttributeValue(parser, i)});
                            }

                            log(xmlSb, ">", new Object[0]);
                            break;
                        case 3:
                            e.setLength(e.length() - "\t".length());
                            log(xmlSb, "%s</%s%s>", new Object[]{e, getNamespacePrefix(parser.getPrefix()), parser.getName()});
                            break;
                        case 4:
                            log(xmlSb, "%s%s", new Object[]{e, parser.getText()});
                    }
                }

                return xmlSb.toString();
            }
        } catch (Exception var14) {
            var14.printStackTrace();
        } finally {
            parser.close();
        }

        return xmlSb.toString();
    }

    private static String getNamespacePrefix(String prefix) {
        return prefix != null && prefix.length() != 0?prefix + ":":"";
    }

    private static String getAttributeValue(XmlResourceParser parser, int index) {
        int type = parser.getAttributeValueType(index);
        int data = parser.getAttributeValueData(index);
        return type == 3?parser.getAttributeValue(index):(type == 2?String.format("?%s%08X", new Object[]{getPackage(data), Integer.valueOf(data)}):(type == 1?String.format("@%s%08X", new Object[]{getPackage(data), Integer.valueOf(data)}):(type == 4?String.valueOf(Float.intBitsToFloat(data)):(type == 17?String.format("0x%08X", new Object[]{Integer.valueOf(data)}):(type == 18?(data != 0?"true":"false"):(type == 5?Float.toString(complexToFloat(data)) + DIMENSION_UNITS[data & 15]:(type == 6?Float.toString(complexToFloat(data)) + FRACTION_UNITS[data & 15]:(type >= 28 && type <= 31?String.format("#%08X", new Object[]{Integer.valueOf(data)}):(type >= 16 && type <= 31?String.valueOf(data):String.format("<0x%X, type 0x%02X>", new Object[]{Integer.valueOf(data), Integer.valueOf(type)}))))))))));
    }

    private static String getPackage(int id) {
        return id >>> 24 == 1?"android:":"";
    }

    private static void log(StringBuilder xmlSb, String format, Object... arguments) {
        log(true, xmlSb, format, arguments);
    }

    private static void log(boolean newLine, StringBuilder xmlSb, String format, Object... arguments) {
        xmlSb.append(String.format(format, arguments));
        if(newLine) {
            xmlSb.append("\n");
        }

    }

    private static float complexToFloat(int complex) {
        return (float)(complex & -256) * RADIX_MULTS[complex >> 4 & 3];
    }
}
