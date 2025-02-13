package ecc_2024.sogonsogon.service;

import ecc_2024.sogonsogon.dto.BookmarkDto;
import ecc_2024.sogonsogon.entity.Bookmark;
import ecc_2024.sogonsogon.repository.BookmarkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BookmarkService {
    @Autowired
    private BookmarkRepository bookmarkRepository;

    public Bookmark save(BookmarkDto bookmarkDto) {
        Bookmark bookmark = bookmarkDto.toEntity();
        if(bookmark.getId()!=null){
            return null;
        }
        return bookmarkRepository.save(bookmark);
    }
}
