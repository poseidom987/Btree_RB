public class Node {
    int data;
    Node left;
    Node right;
    Node parent;
    boolean color;

    public static final boolean RED = true;
    public static final boolean BLACK = false;

    public Node(int data) {
        this.data = data;
        this.color = RED;  // Todo novo nó começa como vermelho
    }

    public Node add(int valor) {
        if (valor < this.data) {
            if (left != null) {
                left = left.add(valor);
            } else {
                left = new Node(valor);
                left.parent = this;
            }
        } else {
            if (right != null) {
                right = right.add(valor);
            } else {
                right = new Node(valor);
                right.parent = this;
            }
        }
        return this;  // Retorna o próprio nó
    }

    public void show() {
        if (left != null) {
            left.show();
        }
        System.out.println("valor:"+ data );
        if(color)System.out.println("red");
        else System.out.println("black");

        if (right != null) {
            right.show();
        }
    }

    public int size() {
        int size = 1;
        if (left != null) size += left.size();
        if (right != null) size += right.size();
        return size;
    }

    public int soma() {
        int soma = this.data;
        if (left != null) soma += left.soma();
        if (right != null) soma += right.soma();
        return soma;
    }
}
