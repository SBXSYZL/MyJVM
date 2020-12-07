package jvm.runtimeDataArea.shared.heap.info.dependence;

/**
 * @author 22454
 */
public class MethodDescriptorParser {
    private String raw;
    private int offset;
    private MethodDescriptor parsed;

    public static MethodDescriptor parseMethodDescriptorParser(String descriptor) {
        MethodDescriptorParser parser = new MethodDescriptorParser();
        return parser.parse(descriptor);
    }

    private MethodDescriptor parse(String descriptor) {
        this.raw = descriptor;
        this.parsed = new MethodDescriptor();
        this.startParameters();
        this.parseParameterTypes();
        this.endParameters();
        this.parseReturnType();
        this.finish();
        return this.parsed;
    }

    private void finish() {
        if (this.offset != this.raw.length()) {
            this.causePanic();
        }
    }

    private void parseReturnType() {
        while (true) {
            String type = this.parseFieldType();
            if ("".equals(type)) {
                break;
            }
            this.parsed.addParameterType(type);
        }
    }

    private void endParameters() {
        if (this.readUint8() != ')') {
            causePanic();
        }
    }

    private void parseParameterTypes() {
        while (true) {
            String type = this.parseFieldType();
            if ("".equals(type)) {
                break;
            }
            this.parsed.addParameterType(type);
        }
    }

    private String parseFieldType() {
        switch (this.readUint8()) {
            case 'B':
                return "B";
            case 'C':
                return "C";
            case 'D':
                return "D";
            case 'F':
                return "F";
            case 'I':
                return "I";
            case 'J':
                return "J";
            case 'S':
                return "S";
            case 'Z':
                return "Z";
            case 'L':
                return this.parseObjectType();
            case '[':
                return this.parseArrayType();
            default:
                this.unReadUint8();
                return "";
        }
    }

    private void unReadUint8() {
        this.offset--;
    }

    private String parseArrayType() {
        int arrayStart = this.offset - 1;
        this.parseFieldType();
        int arrayEnd = this.offset;
        return this.raw.substring(arrayStart, arrayEnd);
    }

    private String parseObjectType() {
        String unRead = this.raw.substring(this.offset);
        int semicolonIndex = unRead.indexOf(";");
        if (semicolonIndex == -1) {
            this.causePanic();
            return "";
        }
        int objStart = this.offset - 1;
        int objEnd = this.offset + semicolonIndex + 1;
        this.offset = objEnd;
        return this.raw.substring(objStart, objEnd);
    }

    private void startParameters() {
        if (this.readUint8() != '(') {
            causePanic();
        }
    }


    private void causePanic() {
        throw new RuntimeException("Bad Descriptor: " + this.raw);
    }

    private byte readUint8() {
        byte[] bytes = this.raw.getBytes();
        byte b = bytes[this.offset++];
        return b;
    }
}
