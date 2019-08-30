package exercises.reusing;

class Tool {
    void used() {
        System.out.println("tool gets used");
    }
}

public class E01 {
    private Tool tool;

    void useTool() {
        if (tool == null)
            tool = new Tool();
        tool.used();
    }

    public static void main(String[] args) {
        new E01().useTool();
    }
}
