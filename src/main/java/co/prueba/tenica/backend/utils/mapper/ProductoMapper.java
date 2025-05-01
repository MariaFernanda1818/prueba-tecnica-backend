package co.prueba.tenica.backend.utils.mapper;

import co.prueba.tenica.backend.dto.ProductoDto;
import co.prueba.tenica.backend.entity.ProductoModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    ProductoModel toModel(ProductoDto dto);

    ProductoDto toDto(ProductoModel model);

}
