package Octopi.example.demo4.Repository;

import Octopi.example.demo4.User.EventLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<EventLog,Long>
{

}
