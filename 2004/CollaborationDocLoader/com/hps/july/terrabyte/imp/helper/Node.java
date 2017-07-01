package com.hps.july.terrabyte.imp.helper;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 23.06.2005
 * Time: 17:51:36
 */
public class Node {

    private Integer ident = null;
    private String name = null;
    private String description = null;
    private Integer parentId = null;
    private Integer level = null;
    private Integer n1 = null;
    private Integer n2 = null;
    private Integer n3 = null;
    private Integer n4 = null;
    private Integer n5 = null;
    private Integer n6 = null;
    private Integer n7 = null;
    private Integer n8 = null;
    private Integer n9 = null;
    private Integer n10 = null;
    private Integer n11 = null;
    private Integer n12 = null;
    private Integer n13 = null;
    private Integer n14 = null;
    private Integer n15 = null;
    private Integer n16 = null;
    private Integer canDeleted = null;

    private ArrayList children = new ArrayList();

    public Node() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIdent() {
        return ident;
    }

    public void setIdent(Integer ident) {
        this.ident = ident;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getCanDeleted() {
        return canDeleted;
    }

    public void setCanDeleted(Integer canDeleted) {
        this.canDeleted = canDeleted;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Collection getChildren() {
        return this.children;
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public Integer getN10() {
        return n10;
    }

    public void setN10(Integer n10) {
        this.n10 = n10;
    }

    public Integer getN11() {
        return n11;
    }

    public void setN11(Integer n11) {
        this.n11 = n11;
    }

    public Integer getN12() {
        return n12;
    }

    public void setN12(Integer n12) {
        this.n12 = n12;
    }

    public Integer getN13() {
        return n13;
    }

    public void setN13(Integer n13) {
        this.n13 = n13;
    }

    public Integer getN14() {
        return n14;
    }

    public void setN14(Integer n14) {
        this.n14 = n14;
    }

    public Integer getN15() {
        return n15;
    }

    public void setN15(Integer n15) {
        this.n15 = n15;
    }

    public Integer getN16() {
        return n16;
    }

    public void setN16(Integer n16) {
        this.n16 = n16;
    }

    public Integer getN1() {
        return n1;
    }

    public void setN1(Integer n1) {
        this.n1 = n1;
    }

    public Integer getN2() {
        return n2;
    }

    public void setN2(Integer n2) {
        this.n2 = n2;
    }

    public Integer getN3() {
        return n3;
    }

    public void setN3(Integer n3) {
        this.n3 = n3;
    }

    public Integer getN4() {
        return n4;
    }

    public void setN4(Integer n4) {
        this.n4 = n4;
    }

    public Integer getN5() {
        return n5;
    }

    public void setN5(Integer n5) {
        this.n5 = n5;
    }

    public Integer getN6() {
        return n6;
    }

    public void setN6(Integer n6) {
        this.n6 = n6;
    }

    public Integer getN7() {
        return n7;
    }

    public void setN7(Integer n7) {
        this.n7 = n7;
    }

    public Integer getN8() {
        return n8;
    }

    public void setN8(Integer n8) {
        this.n8 = n8;
    }

    public Integer getN9() {
        return n9;
    }

    public void setN9(Integer n9) {
        this.n9 = n9;
    }

    protected Object clone() throws CloneNotSupportedException {
        Node node = new Node();
        node.setIdent(getIdent());
        node.setLevel(this.level);
        node.setN1(this.n1);
        node.setN2(this.n2);
        node.setN3(this.n3);
        node.setN4(this.n4);
        node.setN5(this.n5);
        node.setN6(this.n6);
        node.setN7(this.n7);
        node.setN8(this.n8);
        node.setN9(this.n9);
        node.setN10(this.n10);
        node.setN11(this.n11);
        node.setN12(this.n12);
        node.setN13(this.n13);
        node.setN14(this.n14);
        node.setN15(this.n15);
        node.setN16(this.n16);
        node.setName(getName());
        node.setParentId(this.parentId);
        node.setDescription(getDescription());
        return node;
    }

}
