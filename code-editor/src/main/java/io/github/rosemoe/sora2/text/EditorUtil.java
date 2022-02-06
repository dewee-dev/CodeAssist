package io.github.rosemoe.sora2.text;

import io.github.rosemoe.sora.event.SelectionChangeEvent;
import io.github.rosemoe.sora.text.ContentLine;
import io.github.rosemoe.sora.text.ICUUtils;
import io.github.rosemoe.sora.util.IntPair;
import io.github.rosemoe.sora.widget.CodeEditor;

public class EditorUtil {

    public static boolean isWhitespace(CharSequence charSequence) {
        for (int i = 0; i < charSequence.length(); i++) {
            char c = charSequence.charAt(i);
            if (Character.isWhitespace(c)) {
                return true;
            }
        }
        return false;
    }
    public static void selectWord(CodeEditor editor, int line, int column) {
        // Find word edges
        int startLine = line, endLine = line;
        ContentLine lineObj = editor.getText().getLine(line);
        long edges = ICUUtils.getWordEdges(lineObj, column);
        int startColumn = IntPair.getFirst(edges);
        int endColumn = IntPair.getSecond(edges);
        if (startColumn == endColumn) {
            if (startColumn > 0) {
                startColumn--;
            } else if (endColumn < lineObj.length()) {
                endColumn++;
            } else {
                if (line > 0) {
                    int lastColumn = editor.getText().getColumnCount(line - 1);
                    startLine = line - 1;
                    startColumn = lastColumn;
                } else if (line < editor.getLineCount() - 1) {
                    endLine = line + 1;
                    endColumn = 0;
                }
            }
        }
        editor.setSelectionRegion(startLine, startColumn, endLine, endColumn, SelectionChangeEvent.CAUSE_LONG_PRESS);
    }
}