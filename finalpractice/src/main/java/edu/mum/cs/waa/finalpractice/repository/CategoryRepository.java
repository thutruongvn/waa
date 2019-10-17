package edu.mum.cs.waa.finalpractice.repository;

import edu.mum.cs.waa.finalpractice.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
