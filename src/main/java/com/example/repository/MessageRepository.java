package com.example.repository;
import org.springframework.stereotype.Repository;
import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{

    List<Message> findByPostedBy(int account_id);
}
