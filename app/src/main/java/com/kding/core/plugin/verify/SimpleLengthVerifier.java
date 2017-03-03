//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding.core.plugin.verify;

import com.kding.core.plugin.verify.PluginOverdueVerifier;
import java.io.File;

public class SimpleLengthVerifier implements PluginOverdueVerifier {
    public SimpleLengthVerifier() {
    }

    public boolean isOverdue(File originPluginFile, File targetExistFile) {
        return originPluginFile.length() != targetExistFile.length();
    }
}
