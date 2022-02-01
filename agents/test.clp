(deftemplate person
	(slot name)
	(slot age)
	(slot height)
)

(deffacts persons
	(person (name Andrew) (age 24) (height 1.85))
	(person (name Jane) (age 23) (height 1.70))
)

(defrule tall-persons  
	(person (name ?n) (height ?h)) 
        (test (> ?h 1.80)) 
        =>
	(printout t ?n " height is " ?h crlf)
)

