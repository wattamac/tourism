package org.uhafactory.test.api.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.uhafactory.test.tourism.program.Program;
import org.uhafactory.test.tourism.region.Region;

@Mapper
public interface DtoMapper {
    DtoMapper MAPPER = Mappers.getMapper(DtoMapper.class);
    @Mapping(source = "code", target = "region")
    RegionDto toDto(Region region);

    ProgramDto toDto(Program program);

    Program toEntity(ProgramDto programDto);
}
