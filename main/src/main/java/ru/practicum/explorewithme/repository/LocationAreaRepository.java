package ru.practicum.explorewithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.explorewithme.model.location.LocationArea;

public interface LocationAreaRepository extends JpaRepository<LocationArea, Long> {
}
