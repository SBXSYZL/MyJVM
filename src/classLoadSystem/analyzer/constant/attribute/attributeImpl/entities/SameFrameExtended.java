package classLoadSystem.analyzer.constant.attribute.attributeImpl.entities;

/**
 * @author 22454
 */
public class SameFrameExtended {
    public final static int SAME_FRAME_EXTENDED = 251;
    private int frameType = SAME_FRAME_EXTENDED;
    private int offsetDelta;

    public SameFrameExtended(int offsetDelta) {
        this.offsetDelta = offsetDelta;
    }
}
