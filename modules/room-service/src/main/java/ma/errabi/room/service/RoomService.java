package ma.errabi.room.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.errabi.exception.NotFoundException;
import ma.errabi.exception.SystemException;
import ma.errabi.room.RoomDTO;
import ma.errabi.room.mapper.RoomMapper;
import ma.errabi.room.repository.RoomRepository;
import ma.errabi.utils.ErrorConstants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            log.error("Unexpected error while fetching rooms: {}", e.getMessage(), e);
            throw new SystemException(ErrorConstants.SERVER_ERROR_DESC);
        }
    }

    @Transactional(readOnly = true)
    public RoomDTO getRoomById(Long id) {
        log.info("Fetching room details by ID: {}", id);
        try {
            return roomRepository.findById(id)
                    .map(roomMapper::toDto)
                    .orElseThrow(() -> new NotFoundException("Room not found with ID: " + id));
        } catch (Exception e) {
            log.error("Unexpected error while fetching room: {}", e.getMessage(), e);
            throw new SystemException(ErrorConstants.SERVER_ERROR_DESC);
        }
    }
}
