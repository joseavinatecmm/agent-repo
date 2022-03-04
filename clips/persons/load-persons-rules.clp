
;; Defining a rule for finding persons names and printing such names

(defrule my-rule1
   (person (name ?n)) => (printout t ?n  crlf ))

;; Defining a rule for finding persons ages and printing such ages

(defrule my-rule2
   (person (age ?a)) => (printout t ?a  crlf ))


;; Defining a rule for finding female persons names and printing such names

(defrule my-rule3
   (person (gender female) (name ?x)) => (printout t ?x " is female" crlf ))


;; Defining a rule for finding persons partners

(defrule my-rule4
   (person (partner ?p) (name ?n)) => (printout t ?p " is " ?n "'s partner" crlf ))


;;Defining a rule for finding and printing facts that are female persons

(defrule my-rule5
     ?p <-  (person (gender female)) => (printout t ?p " is female" crlf))


;; Defining a rule for finding male persons, older than 20 years, and printing their names


