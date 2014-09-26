'''
Created on Sep 22, 2014
''from enity.ae_mate import AEMate
//matesInRoom = ndb.KeyProperty(kind=AEMate,repeated=True)
@author: labeebp
'''

from google.appengine.ext import ndb


class AERoom(ndb.Model):
    '''
    NDB model class that store values of a Room 
    '''
    roomId = ndb.IntegerProperty()
    name = ndb.StringProperty()
    description = ndb.StringProperty()
    ownerMateId = ndb.StringProperty()
    
    


    def __init__(self, params):
        '''
        Constructor
        '''
        