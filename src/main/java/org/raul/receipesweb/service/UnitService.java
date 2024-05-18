package org.raul.receipesweb.service;

import lombok.RequiredArgsConstructor;
import org.raul.receipesweb.dto.UnitDTO;
import org.raul.receipesweb.exception.ResourceNotFoundException;
import org.raul.receipesweb.model.Unit;
import org.raul.receipesweb.repository.UnitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UnitService {

    private final UnitRepository unitRepository;

    public List<UnitDTO> getAllUnits() {
        List<Unit> unitList = unitRepository.findAll();

        return unitList.stream().map(unit -> new UnitDTO(
                unit.getId(),
                unit.getName()
        )).collect(Collectors.toList());
    }

    public UnitDTO getUnitById(Long id) {
        Unit unit = unitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't found recipe with id :: " + id));

        return new UnitDTO(
                unit.getId(),
                unit.getName());
    }

    public UnitDTO addUnit(UnitDTO dto) {
        Unit unit = new Unit();
        unit.setName(dto.getName());

        Unit saved = unitRepository.save(unit);

        return new UnitDTO(
                saved.getId(),
                saved.getName());
    }

    public void deleteUnit(Long id) {
        Unit unit = unitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't found recipe with id :: " + id));

        unitRepository.delete(unit);
    }
}
