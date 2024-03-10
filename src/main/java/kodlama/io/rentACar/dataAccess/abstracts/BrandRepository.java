package kodlama.io.rentACar.dataAccess.abstracts;

import kodlama.io.rentACar.entities.concretes.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand,Integer> { // ürünümüzü ve primaryk'nin veri tipini giriyoruz


    boolean existsByName(String name); // spring jpa keywordsten dolayı concretes olmadan bu metodu inteface içinde implemente etmeden yazabildik----bu kodla varmı yok mu ona baktık
}
