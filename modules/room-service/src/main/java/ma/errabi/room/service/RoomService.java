package ma.errabi.room.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.errabi.room.RoomDTO;
import ma.errabi.room.mapper.RoomMapper;
import ma.errabi.room.repository.RoomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    @Transactional(readOnly = true)
    public Page<RoomDTO> getAllRooms(int page, int size) {
        log.info("Fetching all rooms with pagination - page: {}, size: {}", page, size);
        try {
            Pageable pageable = PageRequest.of(page, size);
            return roomRepository.findAll(pageable).map(roomMapper::toDto);
        } catch (Exception e) {
            log.error("Error while fetching rooms - page: {}, size: {}. Exception: {}", page, size, e.getMessage(), e);
            throw new RuntimeException("Unable to fetch rooms. Try again later.");
        }
    }

    @Transactional(readOnly = true)
    public Optional<RoomDTO> getRoomById(Long id) {
        log.info("Fetching room details by ID: {}", id);
        try {
            Optional<RoomDTO> room = roomRepository.findById(id).map(roomMapper::toDto);
            if (room.isPresent()) {
                log.info("Successfully fetched room details for ID: {}", id);
            } else {
                log.warn("No room found for ID: {}", id);
            }
            return room;
        } catch (Exception e) {
            log.error("Error while fetching room by ID: {}. Exception: {}", id, e.getMessage(), e);
            throw new RuntimeException("Unable to fetch room details. Try again later.");
        }
    }

}
