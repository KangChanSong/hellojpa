package com.example.jpashop.domain.visitors;

import com.example.jpashop.domain.item.Album;
import com.example.jpashop.domain.item.Book;
import com.example.jpashop.domain.item.Movie;

public class PrintVisitor implements Visitor{
    @Override
    public void visit(Book book) {
        System.out.println("========== 조회 쿼리 실행 =========");
        System.out.println("book.getClass() = " + book.getClass());
        System.out.println("book.getName() = " + book.getName());
    }

    @Override
    public void visit(Album album) {

    }

    @Override
    public void visit(Movie movie) {

    }
}
