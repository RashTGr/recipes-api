package org.raul.receipesweb.controller;

import lombok.RequiredArgsConstructor;
import org.raul.receipesweb.dto.UnitDTO;
import org.raul.receipesweb.model.Unit;
import org.raul.receipesweb.service.UnitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/unit")
public class UnitController {

    private final UnitService unitService;

    @GetMapping
    public ResponseEntity<List<UnitDTO>> getAllUnits() {
        List<UnitDTO> unitDTOs = unitService.getAllUnits();

        return ResponseEntity.ok(unitDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnitDTO> getById(@PathVariable Long id) {
        UnitDTO unit = unitService.getUnitById(id);

        return ResponseEntity.ok(unit);
    }

    @PostMapping
    public ResponseEntity<UnitDTO> addNewUnit(@RequestBody UnitDTO dto) {
        UnitDTO saved = unitService.addUnit(dto);

        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUUnit(@PathVariable Long id) {
        unitService.deleteUnit(id);

        return ResponseEntity.noContent().build();
    }
}