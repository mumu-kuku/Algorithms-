package Linear;

// 手写实现基于两个双向队列的撤销重做管理器
public class UndoRedoManager<E> {
    private MyLinkedDeque<E> undoStack; // 撤销栈
    private MyLinkedDeque<E> redoStack; // 重做栈
    private final int maxSize = 50; // 最大撤销步数

    // 无参构造方法
    public UndoRedoManager() {
        undoStack = new MyLinkedDeque<>();
        redoStack = new MyLinkedDeque<>();
    }

    // 复制构造方法
    public UndoRedoManager(UndoRedoManager<E> other) {
        undoStack = new MyLinkedDeque<>(other.undoStack);
        redoStack = new MyLinkedDeque<>(other.redoStack);
    }

    // 添加一个元素到撤销栈
    public void Add(E e) {
        // 如果撤销栈已满，则删除最旧的元素
        if (undoStack.size() >= maxSize) {
            undoStack.popLast();
        }
        // 将新元素添加到撤销栈的顶部
        undoStack.pushFirst(e);
    }

    // 撤销操作，即返回上一个操作，并将其从撤销栈中放出，放入重做栈中
    public E Undo() {
        // 如果撤销栈为空，则无法撤销，返回null
        if (undoStack.isEmpty()) {
            return null;
        } else {
            // 将撤销栈的顶部元素弹出，并将其放入重做栈的顶部
            E e = undoStack.popFirst();
            redoStack.pushFirst(e);
            // 返回被撤销的元素
            return e;
        }
    }

    // 撤销操作，返回上n个操作，并将其从撤销栈中放出，放入重做栈中
    public E Undo(int n) {
        // 如果n小于等于0或者n大于撤销栈的大小，则无法撤销，返回null
        if (n <= 0 || n > undoStack.size()) return null;
        while (n > 0) {
            // 将撤销栈的顶部元素弹出，并将其放入重做栈的顶部
            E e = undoStack.popFirst();
            redoStack.pushFirst(e);
            n--;
        }
        // 返回被撤销的元素
        return redoStack.popFirst();
    }

    // 撤销所有操作，即清空撤销栈，并将所有操作放入重做栈中
    public E UndoAll() {
        // 如果撤销栈为空，则无法撤销，返回null
        if (undoStack.isEmpty()) {
            return null;
        } else {
            // 将撤销栈的所有元素弹出，并将其放入重做栈的顶部
            E e = undoStack.popLast();
            while (!undoStack.isEmpty()) {
                redoStack.pushFirst(undoStack.popFirst());
            }
            // 返回被撤销的元素
            redoStack.pushFirst(e);
            return e;
        }
    }

    // 查看上一个操作，即返回撤销栈的顶部元素，但不将其从栈中弹出
    public E PeekUndo() {
        if (undoStack.isEmpty()) {
            return null;
        } else {
            return undoStack.peekFirst();
        }
    }

    // 查看所有操作，即返回撤销栈的所有元素，但不将其从栈中弹出
    public String PeekUndoAll() {
        if (undoStack.isEmpty()) {
            return null;
        } else {
            return undoStack.toString();
        }
    }

    // 重做操作，即返回上一个撤销操作，并将其从重做栈中放出，放入撤销栈中
    public E Redo() {
        // 如果重做栈为空，则无法重做，返回null
        if (redoStack.isEmpty()) {
            return null;
        } else {
            // 将重做栈的顶部元素弹出，并将其放入撤销栈的顶部
            E e = redoStack.popFirst();
            undoStack.pushFirst(e);
            return e;
        }
    }

    // 重做操作，返回上n个操作，并将其从重做栈中放出，放入撤销栈中
    public E Redo(int n) {
        // 如果n小于等于0或者n大于重做栈的大小，则无法重做，返回null
        if (n <= 0 || n > redoStack.size()) return null;
        while (n > 0) {
            // 将重做栈的顶部元素弹出，并将其放入撤销栈的顶部
            E e = redoStack.popFirst();
            undoStack.pushFirst(e);
            n--;
        }
        // 返回被重做的元素
        return undoStack.popFirst();
    }

    // 重做所有操作，即清空重做栈，并将所有操作放入撤销栈中
    public E RedoAll() {
        // 如果重做栈为空，则无法重做，返回null
        if (redoStack.isEmpty()) {
            return null;
        } else {
            // 将重做栈的所有元素弹出，并将其放入撤销栈的顶部
            E e = redoStack.popLast();
            while (!redoStack.isEmpty()) {
                undoStack.pushFirst(redoStack.popFirst());
            }
            // 返回被重做的元素
            undoStack.pushFirst(e);
            return e;
        }
    }

    // 查看下一个操作，即返回重做栈的顶部元素，但不将其从栈中弹出
    public E PeekRedo() {
        // 如果重做栈为空，则无法重做，返回null
        if (redoStack.isEmpty()) {
            return null;
        } else {
            // 返回重做栈的顶部元素，但不将其从栈中弹出
            return redoStack.peekFirst();
        }
    }

    // 查看所有操作，即返回重做栈的所有元素，但不将其从栈中弹出
    public String PeekRedoAll() {
        // 如果重做栈为空，则无法重做，返回null
        if (redoStack.isEmpty()) {
            return null;
        } else {
            // 返回重做栈的所有元素，但不将其从栈中弹出
            return redoStack.toString();
        }
    }

    // 查看所有操作，即返回撤销栈和重做栈的所有元素，但不将其从栈中弹出
    public String PeekAll() {
        // 如果撤销栈和重做栈都为空，则无法查看，返回null
        if (undoStack.isEmpty() && redoStack.isEmpty()) {
            return null;
        } else {
            // 返回撤销栈和重做栈的所有元素，但不将其从栈中弹出
            return undoStack.toString() + "\n" + redoStack.toString();
        }
    }
}
