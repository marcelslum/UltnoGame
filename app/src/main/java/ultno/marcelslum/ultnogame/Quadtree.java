package ultno.marcelslum.ultnogame;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 02/08/2016.
 */
public class Quadtree {

    private final static int TOP_LEFT = 0;
    private final static int TOP_RIGHT = 1;
    private final static int BOTTOM_LEFT = 2;
    private final static int BOTTOM_RIGHT = 3;

    Node root;

    public static ArrayList<Entity> outs;

    public Quadtree(RectangleM bounds, int maxDepth, int maxChildren){
        outs = new ArrayList<Entity>();
        this.root = new Node(bounds, 0, maxDepth, maxChildren);

    }

    public void insert(Entity item){
        this.root.insert(item);
    }

    public void clear(){
        this.root.clear();
    }

    public ArrayList<Entity> retrieve(Entity item){

        this.outs = new ArrayList<Entity>();
        this.root.retrieve(item, this.outs);
        return this.outs;
    }

    class Node{
        public RectangleM bounds;
        public int depth;
        public int maxDepth;
        public int maxChildren;
        public ArrayList<Entity> children;
        public ArrayList<Entity> stuckChildren;
        public Node[] nodes;
        private final static int TOP_LEFT = 0;
        private final static int TOP_RIGHT = 1;
        private final static int BOTTOM_LEFT = 2;
        private final static int BOTTOM_RIGHT = 3;


        public Node(RectangleM bounds, int depth, int maxDepth, int maxChildren){
            this.bounds = bounds;
            this.depth = depth;
            this.maxDepth = maxDepth;
            this.maxChildren = maxChildren;
            this.children = new ArrayList<Entity>();
            this.stuckChildren = new ArrayList<Entity>();
        }

        public void insert(Entity item){

            ////System.out.println("inserindo item "+item.name);


            RectangleM itemBounds = item.getQuadtreeData();
            if (this.nodes != null){
                int index = this.findIndex(itemBounds);

                ////System.out.println("achado o index "+index +" para o item "+item.name);

                Node node = this.nodes[index];
                if (itemBounds.x >= node.bounds.x &&
                        itemBounds.x + itemBounds.width <= node.bounds.x + node.bounds.width &&
                        itemBounds.y >= node.bounds.y &&
                        itemBounds.y + itemBounds.height <= node.bounds.y + node.bounds.height)
                {
                    node.insert(item);
                } else {
                    this.stuckChildren.add(item);
                }
                return;
            }

            this.children.add(item);

            if (!(this.depth >= this.maxDepth) && this.children.size() > this.maxChildren){
                this.subdivide();
                for (int i = 0; i < this.children.size(); i++){
                    this.insert(this.children.get(i));
                }
                this.children.clear();
            }
        }

        public ArrayList<Entity> retrieve(Entity item, ArrayList<Entity> outs){
            RectangleM itemBounds = item.getQuadtreeData();

            if (this.nodes != null){
                int index = this.findIndex(itemBounds);

                ////System.out.println("index no retrieve do item " + item.name + " index: " + index + " nível: "+ this.depth);
                Node node = this.nodes[index];
                if (itemBounds.x >= node.bounds.x &&
                        itemBounds.x + itemBounds.width <= node.bounds.x + node.bounds.width &&
                        itemBounds.y >= node.bounds.y &&
                        itemBounds.y + itemBounds.height <= node.bounds.y + node.bounds.height)
                {

                    //System.out.println("ativando função recursiva");
                    node.retrieve(item, outs);
                } else {

                    //System.out.println("item está em mais de um node");

                    if (itemBounds.x <= this.nodes[TOP_RIGHT].bounds.x){
                        if (itemBounds.y <= this.nodes[BOTTOM_LEFT].bounds.y){
                            this.nodes[TOP_LEFT].getAllContent(outs);
                        }
                        if (itemBounds.y + itemBounds.height > this.nodes[BOTTOM_LEFT].bounds.y){
                            this.nodes[BOTTOM_LEFT].getAllContent(outs);
                        }
                    }

                    if (itemBounds.x + itemBounds.width > this.nodes[TOP_RIGHT].bounds.x){
                        if (itemBounds.y <= this.nodes[BOTTOM_RIGHT].bounds.y){
                            this.nodes[TOP_RIGHT].getAllContent(outs);
                        }
                        if (itemBounds.y + itemBounds.height > this.nodes[BOTTOM_RIGHT].bounds.y){
                            this.nodes[BOTTOM_RIGHT].getAllContent(outs);
                        }
                    }

                }
            }

            outs.addAll(this.stuckChildren);
            outs.addAll(this.children);

            return outs;
        }

        public void getAllContent(ArrayList<Entity> outs){

            //System.out.println("getAllContent");


            if (this.nodes != null){
                for (int i = 0; i < this.nodes.length; i++){
                    this.nodes[i].getAllContent(outs);
                }
            }

            //System.out.println("getAllContent2");


            outs.addAll(this.stuckChildren);

            //System.out.println("getAllContent3");
            outs.addAll(this.children);
        }

        public void clear() {
            //Log.e("clear1", " " + this.depth);
            this.stuckChildren.clear();
            //Log.e("clear2", " " + this.depth);
            this.children.clear();
            //Log.e("clear3", " " + this.depth);
            if (this.nodes != null) {
                for (int i = 0; i < this.nodes.length; i++) {
                    this.nodes[i].clear();
                    //Log.e("clear4", " " + this.depth);
                }
            }

            this.nodes = null;
        }

        public int findIndex(RectangleM bounds){

            ////System.out.println("find index bounds.x "+bounds.x +" em relação ao node de bounds x "+this.bounds.x+ " e width" + this.bounds.width);

            boolean left;

            if (bounds.x > this.bounds.x + this.bounds.width / 2){
                left = false;
            } else {
                left =  true;
            }

            boolean top;
            if (bounds.y > this.bounds.y + this.bounds.height / 2){
                top = false;
            } else {
                top =  true;
            }

            int index = TOP_LEFT;
            if (left){
                if (!top){
                    index = BOTTOM_LEFT;
                }
            } else {
                if (top){
                    index = TOP_RIGHT;
                } else {
                    index = BOTTOM_RIGHT;
                }
            }
            return index;
        }

        public void subdivide(){

            //System.out.println("subdividindo o nível " + this.depth);

            int depth = this.depth + 1;

            float bx = this.bounds.x;
            float by = this.bounds.y;

            float b_w_h = (this.bounds.width / 2);
            float b_h_h = (this.bounds.height / 2);
            float bx_b_w_h = bx + b_w_h;
            float by_b_h_h = by + b_h_h;

            this.nodes = new Node[4];

            //top left
            this.nodes[TOP_LEFT] = new Node(new RectangleM(bx, by, b_w_h, b_h_h), depth, maxDepth, maxChildren);

            //top right
            this.nodes[TOP_RIGHT] = new Node(new RectangleM(bx_b_w_h, by, b_w_h, b_h_h), depth, maxDepth, maxChildren);

            //bottom left
            this.nodes[BOTTOM_LEFT] = new Node(new RectangleM(bx, by_b_h_h, b_w_h, b_h_h), depth, maxDepth, maxChildren);

            //bottom right
            this.nodes[BOTTOM_RIGHT] = new Node(new RectangleM(bx_b_w_h, by_b_h_h, b_w_h, b_h_h), depth, maxDepth, maxChildren);

        }

    }
}
