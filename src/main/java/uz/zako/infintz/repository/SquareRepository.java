package uz.zako.infintz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.zako.infintz.entity.Square;

@Repository
public interface SquareRepository extends JpaRepository<Square,Integer> {
}
