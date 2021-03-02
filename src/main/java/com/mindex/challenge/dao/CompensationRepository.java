package com.mindex.challenge.dao;

import com.mindex.challenge.data.Compensation;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

/*
 * 
@author: Saurabh Shukla

Class explanation: This Compensation repository is interface that extends MongoRepository and declares
					method under it.
                    . 
*/

@Repository
public interface CompensationRepository extends MongoRepository<Compensation, String> {
    List<Compensation> findAll();
}
