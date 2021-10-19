package com.example.jpashop.domain.visitors;

import com.example.jpashop.domain.item.Album;
import com.example.jpashop.domain.item.Book;
import com.example.jpashop.domain.item.Movie;

public interface Visitor {
    void visit(Book book);
    void visit(Album album);
    void visit(Movie movie);
}
