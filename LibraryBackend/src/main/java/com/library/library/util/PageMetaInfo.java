package com.library.library.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PageMetaInfo {

    private int page;
    private int size;
    private int totalElements;
    private int totalPages;
}
