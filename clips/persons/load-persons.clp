
;;Defining a template for representing/modelling persons in Jess Working Memory

(deftemplate person
   	(slot name )
   	(slot gender)
   	(slot age (type INTEGER))
   	(slot partner)
)

;;Storing four persons (four facts) in Jess Working Memory via deffacts function 

(deffacts partnership
        (person  (name Fred)  (gender  male)   (age 26)  (partner Susan))
   	(person  (name Susan) (gender female)  (age 24) (partner Fred))
   	(person  (name Andy)  (gender male)    (age 25)   (partner Sara))
	(person  (name Alice) (gender female)  (age 23)   (partner Bob))
)

