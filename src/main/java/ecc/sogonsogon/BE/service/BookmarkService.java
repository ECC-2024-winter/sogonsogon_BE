package ecc.sogonsogon.BE.service;

import ecc.sogonsogon.BE.dto.BookmarkDto;
import ecc.sogonsogon.BE.entity.Bookmark;
import ecc.sogonsogon.BE.entity.Folder;
import ecc.sogonsogon.BE.entity.Place;
import ecc.sogonsogon.BE.entity.User;
import ecc.sogonsogon.BE.repository.BookmarkRepository;
import ecc.sogonsogon.BE.repository.FolderRepository;
import ecc.sogonsogon.BE.repository.PlaceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class BookmarkService {
    @Autowired
    private BookmarkRepository bookmarkRepository;
    @Autowired
    private FolderRepository folderRepository;
    @Autowired
    private PlaceRepository placeRepository;

    @Transactional
    public Bookmark save(User user, BookmarkDto bookmarkDto) {
        Place place = placeRepository.findById(Long.valueOf(bookmarkDto.getPlaceId())).orElseThrow(() -> new IllegalArgumentException("장소를 찾을 수 없습니다."));
        Folder folder=folderRepository.findById(bookmarkDto.getFolderId()).orElseThrow(() -> new IllegalArgumentException("저장 목록을 찾을 수 없습니다."));

        Bookmark bookmark = Bookmark.creteBookmark(bookmarkDto, user, place, folder);

        return bookmarkRepository.save(bookmark);
    }
}
