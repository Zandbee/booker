package org.strokova.booker.client.jacksonUtils;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 21.11.2016.
 */
public class JacksonPageImpl<T> extends PageImpl<T> {
    private static final long serialVersionUID = 1L;
    private int number;
    private int size;
    private int totalPages;
    private int numberOfElements;
    private long totalElements;
    private boolean previous;
    private boolean first;
    private boolean next;
    private boolean last;
    private List<T> content;
    private Sort sort;

    public JacksonPageImpl() {
        super(new ArrayList<>());
    }

    @Override
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    @Override
    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public boolean isPrevious() {
        return previous;
    }

    public void setPrevious(boolean previous) {
        this.previous = previous;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    @Override
    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @JsonDeserialize(using = JacksonSortDeserializer.class)
    public JacksonPageImpl setSort(Sort sort) {
        this.sort = sort;
        return this;
    }

    public Page<T> pageImpl() {
        return new PageImpl<>(getContent(), new PageRequest(getNumber(),
                getSize(), getSort()), getTotalElements());
    }
}
