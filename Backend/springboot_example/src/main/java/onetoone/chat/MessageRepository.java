

package onetoone.chat;

import org.springframework.data.jpa.repository.JpaRepository;

//MessageRepository (MessageRepository.java):
//
//        This is an interface that extends JpaRepository<Message, Long>, providing methods for performing database operations on Message entities.
//        It includes custom methods like markMessageAsSeen and updateMessageStatus which use JPQL queries to update message statuses in the database.

public interface MessageRepository extends JpaRepository<Message, Long>{

}

