package br.com.badbit.algafoods.core.data;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class PageWrapper<T> extends PageImpl<T> {

    private Pageable pageable;

    public PageWrapper(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
        this.pageable = pageable;
    }

    @Override
    public Pageable getPageable() {
        return this.pageable;
    }
}
