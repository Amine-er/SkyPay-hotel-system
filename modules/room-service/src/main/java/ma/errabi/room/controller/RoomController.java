package ma.errabi.room.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import ma.errabi.room.RoomDTO;
import ma.errabi.room.service.RoomService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping
    @Operation(summary = "Get all rooms")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Pagination parameters")
    @ApiResponse(responseCode = "200", description = "Successfully fetched all rooms")
    public ResponseEntity<Page<RoomDTO>> getAllRooms(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<RoomDTO> rooms = roomService.getAllRooms(page, size);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get room by ID")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Room ID")
    @ApiResponse(responseCode = "200", description = "Successfully fetched room by ID")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable Long id) {
        Optional<RoomDTO> room = roomService.getRoomById(id);
        return room.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}