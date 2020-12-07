package jvm.runtimeDataArea.shared.heap.info.dependence;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 22454
 */
public class MethodDescriptor {
    private List<String> parameterTypes;
    private String returnType;

    public MethodDescriptor() {
        parameterTypes = new ArrayList<>();
    }

    public void addParameterType(String type) {
        this.parameterTypes.add(type);
    }

    public List<String> getParameterTypes() {
        return parameterTypes;
    }

    public String getReturnType() {
        return returnType;
    }

//    public static MethodDescriptor parse(String descriptor) {
//        MethodDescriptor methodDescriptor = new MethodDescriptor();
//        MethodDescriptorParser methodDescriptorParser = new MethodDescriptorParser(descriptor, methodDescriptor);
//
//    }

}
