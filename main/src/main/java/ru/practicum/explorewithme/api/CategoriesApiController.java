package ru.practicum.explorewithme.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.model.category.CategoryDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.practicum.explorewithme.service.CategoryService;
import ru.practicum.explorewithme.service.CompilationService;
import ru.practicum.explorewithme.util.ListModelMapper;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/categories")
public class CategoriesApiController implements CategoriesApi {

    private final ModelMapper modelMapper;

    private final ListModelMapper listModelMapper;

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategories(@Valid @RequestParam(value = "from", required = false, defaultValue = "0") Integer from,
                                                           @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
                return new ResponseEntity<>(listModelMapper.mapList(categoryService.getCategories(from, size), CategoryDto.class),
                        HttpStatus.OK);
    }

    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("catId") Long catId) {
        return new ResponseEntity<>(modelMapper.map(categoryService.getCategoryById(catId), CategoryDto.class),
                HttpStatus.OK);
    }
}
