package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;
import java.util.List;


/*
 * 
@author: Saurabh Shukla

Class explanation: This Compensation service is interface to declare read & create methods.
                    . 
*/

public interface CompensationService {
	List<Compensation> read(String id);
	Compensation create(Compensation compensation);

}
