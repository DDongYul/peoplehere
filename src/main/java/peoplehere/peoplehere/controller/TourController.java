package peoplehere.peoplehere.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import peoplehere.peoplehere.common.exception.TourException;
import peoplehere.peoplehere.common.response.BaseResponse;
import peoplehere.peoplehere.controller.dto.tour.GetTourResponse;
import peoplehere.peoplehere.controller.dto.tour.PostTourRequest;
import peoplehere.peoplehere.controller.dto.tour.PutTourRequest;
import peoplehere.peoplehere.controller.dto.tour.TourDtoConverter;
import peoplehere.peoplehere.domain.Tour;
import peoplehere.peoplehere.service.TourService;
import peoplehere.peoplehere.util.BindingResultUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static peoplehere.peoplehere.common.response.status.BaseExceptionResponseStatus.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tours")
public class TourController {

    private final TourService tourService;

    @PostMapping("/new")
    public BaseResponse<GetTourResponse> addTour(@Valid @RequestBody PostTourRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessages = BindingResultUtils.getErrorMessages(bindingResult);
            throw new TourException(INVALID_TOUR_VALUE, errorMessages);
        }
        log.info("Create tour request: {}", request.getName());
        Tour tour = tourService.createTour(request);
        return new BaseResponse<>(TourDtoConverter.tourToGetTourResponse(tour));
    }

    @PutMapping("/{id}")
    public BaseResponse<GetTourResponse> updateTour(@PathVariable Long id, @Valid @RequestBody PutTourRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessages = BindingResultUtils.getErrorMessages(bindingResult);
            throw new TourException(INVALID_TOUR_VALUE, errorMessages);
        }
        log.info("Update tour request for ID: {}, {}", id, request.getName());
        tourService.modifyTour(id, request);
        return new BaseResponse<>(new GetTourResponse());
    }

    @PatchMapping("/{id}")
    public BaseResponse<Void> deleteTour(@PathVariable Long id) {
        log.info("Delete tour request for ID: {}", id);
        tourService.deleteTour(id);
        return new BaseResponse<>(null);
    }

    @GetMapping("")
    public BaseResponse<Map<String, Object>> getAllTours(
            @RequestParam(required = false) List<String> categories, Pageable pageable) {
        Page<Tour> toursPage;
        if (categories == null || categories.isEmpty()) {
            // 페이징 정보와 랜덤 정렬을 사용하여 모든 투어 조회
            toursPage = tourService.findAllTours(pageable);
        } else {
            // 선택된 카테고리에 따라 투어 조회
            toursPage = tourService.findAllToursByCategory(categories, pageable);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("content", toursPage.getContent().stream().map(TourDtoConverter::tourToGetTourResponse).collect(Collectors.toList()));
        response.put("currentPage", toursPage.getNumber());
        response.put("totalPages", toursPage.getTotalPages());
        response.put("totalElements", toursPage.getTotalElements());
        response.put("size", toursPage.getSize());

        return new BaseResponse<>(response);
    }

    @GetMapping("/{id}")
    public BaseResponse<GetTourResponse> getTour(@PathVariable Long id) {
        log.info("Get tour request for ID: {}", id);
        Tour findTour = tourService.findTourById(id);
        return new BaseResponse<>(TourDtoConverter.tourToGetTourResponse(findTour));
    }
}