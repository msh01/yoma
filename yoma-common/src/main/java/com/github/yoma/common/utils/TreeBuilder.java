package com.github.yoma.common.utils;

import java.util.ArrayList;
import java.util.List;

import com.github.yoma.common.persistence.TreeEntity;

/**
 * Created by Massive on 2016/12/26.
 */
public class TreeBuilder<T extends TreeEntity<T>> {

    /**
     * 默认最顶层的节点ID
     */
    public static final Long DEFAULT_TOP_ID = 0L;

    /**
     * 使用递归方法建树
     *
     * @param treeNodes
     * @return
     */
    public List<T> buildByRecursive(List<T> treeNodes) {
        List<T> trees = new ArrayList<T>();
        for (T treeNode : treeNodes) {
            if (treeNode.getParentId() == DEFAULT_TOP_ID) {
                T children = findChildren(treeNode, treeNodes);
                trees.add(children);
            }
        }
        return trees;
    }

    /**
     * 使用递归方法建树
     *
     * @param treeNodes
     * @return
     */
    public List<T> buildByRecursive(List<T> treeNodes, Long topId) {
        List<T> trees = new ArrayList<T>();
        for (T treeNode : treeNodes) {
            if (treeNode.getParentId().equals(topId)) {
                T children = findChildren(treeNode, treeNodes);
                trees.add(children);
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     *
     * @param treeNodes
     * @return
     */
    public T findChildren(T treeNode, List<T> treeNodes) {
        for (T it : treeNodes) {
            if (treeNode.getId().equals(it.getParentId())) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<T>());
                }
                treeNode.getChildren().add(findChildren(it, treeNodes));
            }
        }
        return treeNode;
    }

    // public static void main(String[] args) {
    //
    // TreeEntity treeNode1 = new TreeEntity("1","广州","0");
    // TreeEntity treeNode2 = new TreeEntity("2","深圳","0");
    //
    // TreeEntity treeNode3 = new TreeEntity("3","天河区",treeNode1);
    // TreeEntity treeNode4 = new TreeEntity("4","越秀区",treeNode1);
    // TreeEntity treeNode5 = new TreeEntity("5","黄埔区",treeNode1);
    // TreeEntity treeNode6 = new TreeEntity("6","石牌",treeNode3);
    // TreeEntity treeNode7 = new TreeEntity("7","百脑汇",treeNode6);
    //
    //
    // TreeEntity treeNode8 = new TreeEntity("8","南山区",treeNode2);
    // TreeEntity treeNode9 = new TreeEntity("9","宝安区",treeNode2);
    // TreeEntity treeNode10 = new TreeEntity("10","科技园",treeNode8);
    //
    //
    // List<TreeEntity> list = new ArrayList<TreeEntity>();
    //
    // list.add(treeNode1);
    // list.add(treeNode2);
    // list.add(treeNode3);
    // list.add(treeNode4);
    // list.add(treeNode5);
    // list.add(treeNode6);
    // list.add(treeNode7);
    // list.add(treeNode8);
    // list.add(treeNode9);
    // list.add(treeNode10);
    //
    // List<TreeEntity> trees = TreeBuilder.bulid(list);
    //
    // List<TreeEntity> trees_ = TreeBuilder.buildByRecursive(list);
    //
    // }

}
