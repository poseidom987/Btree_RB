public class Btree {
    private Node raiz;

    public Btree() {
        raiz = null;
    }

    public void add(int valor) {
        if (raiz != null) {
            raiz = raiz.add(valor);  // Atualiza a raiz após balancear
        } else {
            raiz = new Node(valor);
        }
        fixRedBlackPropertiesAfterInsert(raiz);
    }

    public void show() {
        if (raiz != null) raiz.show();
    }

    public int size() {
        if (raiz != null) {
            return raiz.size();
        } else {
            return 0;
        }
    }

    public int soma() {
        if (raiz != null) {
            return raiz.soma();
        } else {
            return 0;
        }
    }

    // Método para consertar as propriedades Red-Black após inserção
    private void fixRedBlackPropertiesAfterInsert(Node node) {
        Node parent = node.parent;

        if (parent == null) {
            node.color = Node.BLACK; // A raiz deve ser sempre preta
            return;
        }

        if (parent.color == Node.BLACK) {
            return;  // Se o pai é preto, não há problemas
        }

        Node grandparent = parent.parent;
        Node uncle = getUncle(parent);

        if (uncle != null && uncle.color == Node.RED) {
            parent.color = Node.BLACK;
            grandparent.color = Node.RED;
            uncle.color = Node.BLACK;
            fixRedBlackPropertiesAfterInsert(grandparent);
        } else if (parent == grandparent.left) {
            if (node == parent.right) {
                rotateLeft(parent);
                parent = node;
            }
            rotateRight(grandparent);
            parent.color = Node.BLACK;
            grandparent.color = Node.RED;
        } else {
            if (node == parent.left) {
                rotateRight(parent);
                parent = node;
            }
            rotateLeft(grandparent);
            parent.color = Node.BLACK;
            grandparent.color = Node.RED;
        }
    }

    private Node getUncle(Node parent) {
        Node grandparent = parent.parent;
        if (grandparent.left == parent) {
            return grandparent.right;
        } else {
            return grandparent.left;
        }
    }

    private void rotateLeft(Node node) {
        Node rightChild = node.right;
        node.right = rightChild.left;

        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }

        rightChild.parent = node.parent;

        if (node.parent == null) {
            raiz = rightChild;
        } else if (node == node.parent.left) {
            node.parent.left = rightChild;
        } else {
            node.parent.right = rightChild;
        }

        rightChild.left = node;
        node.parent = rightChild;
    }

    private void rotateRight(Node node) {
        Node leftChild = node.left;
        node.left = leftChild.right;

        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }

        leftChild.parent = node.parent;

        if (node.parent == null) {
            raiz = leftChild;
        } else if (node == node.parent.left) {
            node.parent.left = leftChild;
        } else {
            node.parent.right = leftChild;
        }

        leftChild.right = node;
        node.parent = leftChild;
    }
}
