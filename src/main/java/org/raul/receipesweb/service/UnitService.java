package org.raul.receipesweb.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.raul.receipesweb.dto.UnitDTO;
import org.raul.receipesweb.exception.ResourceNotFoundException;
import org.raul.receipesweb.model.Unit;
import org.raul.receipesweb.repository.UnitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UnitService {

    private final UnitRepository unitRepository;

    public List<UnitDTO> getAllUnits() {
        log.info("Fetching all units from the database");

        List<Unit> unitList = unitRepository.findAll();

        return unitList.stream().map(unit -> new UnitDTO(
                unit.getId(),
                unit.getName()
        )).collect(Collectors.toList());
    }

    public UnitDTO getUnitById(Long id) {
        log.info("Fetching unit by ID: {}", id);

        Unit unit = unitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't found recipe with id :: " + id));

        return new UnitDTO(
                unit.getId(),
                unit.getName());
    }

    public UnitDTO addUnit(UnitDTO dto) {
        log.info("Adding new unit with name: {}", dto.getName());

        Unit unit = new Unit();
        unit.setName(dto.getName());

        Unit saved = unitRepository.save(unit);
        log.info("Unit added with ID: {}", saved.getId());

        return new UnitDTO(
                saved.getId(),
                saved.getName());
    }

    public void deleteUnit(Long id) {
        log.info("Deleting unit with ID: {}", id);

        Unit unit = unitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't found recipe with id :: " + id));

        unitRepository.delete(unit);
        log.info("Unit deleted with ID: {}", id);
    }
}
