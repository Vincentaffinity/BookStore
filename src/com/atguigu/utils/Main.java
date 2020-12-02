package com.atguigu.utils;

import sun.reflect.generics.tree.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static ArrayList<Integer> con = new ArrayList<>(1000);
    static ArrayList<Integer> counter = new ArrayList<>(1000);


    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        HashMap<Integer, String> map = new HashMap<>();
//        String string = br.readLine();
//        String[] stringArr = string.split(",");

        //construct();


    }




    public static void construct(){
        int[][] numArr = {{2, 1}, {3, 1}, {4, 1}, {1, 0}}; //id parentId
        HashMap<Integer, Node> map = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < numArr.length; i++){
            int nodeNo = numArr[i][1];
            int nodeNoSon = numArr[i][0];

            set.add(nodeNoSon);
            Node node = map.get(nodeNo);
            Node nodeSon = map.get(nodeNoSon);
            if(node == null) node = new Node(nodeNo, new ArrayList<>());
            if(nodeSon == null) nodeSon = new Node(nodeNoSon, new ArrayList<>());
            node.sub.add(nodeSon);
            //System.out.println(node +" ////" + nodeSon);
            map.put(nodeNo, node);
            map.put(nodeNoSon, nodeSon);
        }
        //System.out.println(map);
        int headNo = -1;
        for(int j = 0; j < numArr.length; j++){
            if(!set.contains(numArr[j][1])){
                headNo = numArr[j][1];
                break;
            }
        }
        Node node = map.get(headNo);
        System.out.println(node);
    }


}


class Node{
    int val;
    List<Node> sub;

    public Node(int val, List<Node> sub) {
        this.val = val;
        this.sub = sub;
    }
    public Node(){

    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                ", sub=" + sub +
                '}';
    }
}

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}