================================== Syst�me ascenseur =================================
Le syst�me ascenseur impl�mente le partern Observer / Observable, pour lequelles 
l'interface utilisateur et le syst�me de contr�le peuvent s'abonner � diff�rents
�v�nements.
Il en existe 3 :
	- notification d'un changement de niveau
	- notification d'un arret
	- notification d'une surcharge

=================================== Capteur Niveau ===================================

Les capteurs de niveaux sont en faite simuler par une classe "CapteurNiveau",
c'est � dire que cette instance teste pour chaque niveau si la position de la 
cabine correspond � un niveau. 

En r�alit� il aurait fallu faire une liste de 
capteur de niveau pour chaque niveau et que ceux-ci d�tecte individuellement 
si la cabine est en face d'eux. Mais ce syst�me aurait n�cessit� beaucoup trop de code.





