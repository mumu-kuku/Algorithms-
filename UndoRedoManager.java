package mumu;

// 手写实现基于两个双向队列的撤销重做管理器
public class UndoRedoManager<E> {
    private MyLinkedDeque<E> undoStack;
    private MyLinkedDeque<E> redoStack;
    private final int maxSize = 50;

    public UndoRedoManager() {
        undoStack = new MyLinkedDeque<>();
        redoStack = new MyLinkedDeque<>();
    }

    public UndoRedoManager(UndoRedoManager<E> other) {
        undoStack = new MyLinkedDeque<>(other.undoStack);
        redoStack = new MyLinkedDeque<>(other.redoStack);
    }

    public void Add(E e) {
        if (undoStack.size() >= maxSize) {
            undoStack.popLast();
        }
        undoStack.pushFirst(e);
    }

    public E Undo() {
        if (undoStack.isEmpty()) {
            return null;
        } else {
            E e = undoStack.popFirst();
            redoStack.pushFirst(e);
            return e;
        }
    }

    public E Undo(int n) {
        if (n <= 0 || n > undoStack.size()) return null;
        while (n > 0) {
            E e = undoStack.popFirst();
            redoStack.pushFirst(e);
            n--;
        }
        return redoStack.popFirst();
    }

    public E UndoAll() {
        if (undoStack.isEmpty()) {
            return null;
        } else {
            E e = undoStack.popLast();
            while (!undoStack.isEmpty()) {
                redoStack.pushFirst(undoStack.popFirst());
            }
            redoStack.pushFirst(e);
            return e;
        }
    }

    public E PeekUndo() {
        if (undoStack.isEmpty()) {
            return null;
        } else {
            return undoStack.peekFirst();
        }
    }

    public String PeekUndoAll() {
        if (undoStack.isEmpty()) {
            return null;
        } else {
            return undoStack.toString();
        }
    }

    public E Redo() {
        if (redoStack.isEmpty()) {
            return null;
        } else {
            E e = redoStack.popFirst();
            undoStack.pushFirst(e);
            return e;
        }
    }

    public E Redo(int n) {
        if (n <= 0 || n > redoStack.size()) return null;
        while (n > 0) {
            E e = redoStack.popFirst();
            undoStack.pushFirst(e);
            n--;
        }
        return undoStack.popFirst();
    }

    public E RedoAll() {
        if (redoStack.isEmpty()) {
            return null;
        } else {
            E e = redoStack.popLast();
            while (!redoStack.isEmpty()) {
                undoStack.pushFirst(redoStack.popFirst());
            }
            undoStack.pushFirst(e);
            return e;
        }
    }

    public E PeekRedo() {
        if (redoStack.isEmpty()) {
            return null;
        } else {
            return redoStack.peekFirst();
        }
    }

    public String PeekRedoAll() {
        if (redoStack.isEmpty()) {
            return null;
        } else {
            return redoStack.toString();
        }
    }

    public String PeekAll() {
        if (undoStack.isEmpty() && redoStack.isEmpty()) {
            return null;
        } else {
            return undoStack.toString() + "\n" + redoStack.toString();
        }
    }
}
