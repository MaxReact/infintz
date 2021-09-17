package uz.zako.infintz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.zako.infintz.entity.Triangle;

@Repository
public interface TriangleRepository extends JpaRepository<Triangle,Integer> {
}
