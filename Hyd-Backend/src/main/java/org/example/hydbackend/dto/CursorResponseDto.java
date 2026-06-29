package org.example.hydbackend.dto;

import java.util.List;

public class CursorResponseDto {
    private List<ProductResponseDto> products;
    private Long nextCursor;
    private boolean hasNext;

    public CursorResponseDto(){

    }
    public CursorResponseDto(List<ProductResponseDto> products, Long nextCursor, boolean hasNext){
        this.products=products;
        this.nextCursor=nextCursor;
        this.hasNext=hasNext;
    }

    public List<ProductResponseDto> getProducts(){
        return products;
    }

    public void setProducts(List<ProductResponseDto> products){
        this.products=products;
    }

    public Long getNextCursor(){
        return nextCursor;
    }
    public void setNextCursor(Long nextCursor){
        this.nextCursor=nextCursor;
    }

    public boolean isHasNext(){
        return hasNext;
    }
    public void setHasNext(boolean hasNext){
        this.hasNext=hasNext;
    }

}
