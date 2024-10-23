public class Btree {
    private Node raiz;

    public Btree() {
        raiz = null;
    }

    public void add(int valor) {
        Node novoNo = new Node(valor);
        raiz = insert(raiz, novoNo);
        fixRedBlackPropertiesAfterInsert(novoNo); // Corrige as propriedades Red-Black
        raiz.color = Node.BLACK; // A raiz deve sempre ser preta
    }

    // Método para inserir um nó na árvore
    private Node insert(Node raiz, Node novoNo) {
        if (raiz == null) {
            return novoNo;
        }

        if (novoNo.data < raiz.data) {
            raiz.left = insert(raiz.left, novoNo);
            raiz.left.parent = raiz;
        } else if (novoNo.data > raiz.data) {
            raiz.right = insert(raiz.right, novoNo);
            raiz.right.parent = raiz;
        }

        return raiz;
    }

    // Método para consertar as propriedades Red-Black após inserção
    private void fixRedBlackPropertiesAfterInsert(Node node) {
        Node parent = node.parent;

        // Caso 1: O nó é a raiz
        if (parent == null) {
            node.color = Node.BLACK; // A raiz deve ser sempre preta
            return;
        }

        // Caso 2: O pai é preto, então a árvore continua válida
        if (parent.color == Node.BLACK) {
            return;
        }

        Node grandparent = parent.parent;
        Node uncle = getUncle(parent);

        // Caso 3: O tio é vermelho, recoloração
        if (uncle != null && uncle.color == Node.RED) {
            parent.color = Node.BLACK;
            uncle.color = Node.BLACK;
            grandparent.color = Node.RED;
            fixRedBlackPropertiesAfterInsert(grandparent); // Verifica para o avô
        } else {
            // Caso 4: Tio é preto ou null, rotações necessárias
            if (parent == grandparent.left) {
                if (node == parent.right) {
                    rotateLeft(parent);
                    parent = node;
                }
                rotateRight(grandparent);
            } else {
                if (node == parent.left) {
                    rotateRight(parent);
                    parent = node;
                }
                rotateLeft(grandparent);
            }

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
}
