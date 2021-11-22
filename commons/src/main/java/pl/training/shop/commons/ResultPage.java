package pl.training.shop.commons;

import lombok.Value;

import java.util.List;

@Value
public class ResultPage<T> {

    List<T> entries;
    Page page;
    int totalEntries;

    public int totalPages() {
        return (int) Math.ceil((double) totalEntries / page.getSize());
    }

}
