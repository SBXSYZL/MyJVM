package classLoadSystem.analyzer;

import static classLoadSystem.analyzer.ClassFile.MAGIC;

/**
 * @author 22454
 */
public class ClassFileAnalyzer {

    private boolean startWithMagic(String[] byteCode) {
        if (byteCode.length < MAGIC.length) {
            return false;
        }
        for (int i = 0; i < MAGIC.length; i++) {
            if (!byteCode[i].equals(MAGIC[i])) {
                return false;
            }
        }
        return true;
    }
}
