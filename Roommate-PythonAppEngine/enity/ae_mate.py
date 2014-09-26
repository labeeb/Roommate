'''
Created on Sep 19, 2014

@author: labeebp
'''
from google.appengine.ext import ndb
from protorpc import messages
from enity.ae_room import AERoom

class AEMateRequest(messages.Message):
    #__metaclass__ = Message
    '''
    messages.Message model class that store values of a mate 
    '''
    username = messages.StringField(1)
    password = messages.StringField(2)
    emailAddress = messages.StringField(3)
    description = messages.StringField(4)
    
class AEMateResponse(messages.Message):
    #__metaclass__ = Message
    '''
    messages.Message model class that store values of a mate 
    '''
    username = messages.StringField(1)
    password = messages.StringField(2)
    mateId = messages.IntegerField(3)
    
class AEMate(ndb.Model):
    #__metaclass__ = Message
    '''
    NDB model class that store values of a mate 
    '''
    username = ndb.StringProperty()
    password = ndb.StringProperty()
    emailAddress = ndb.StringProperty()
    description = ndb.StringProperty()
    mateId = ndb.IntegerProperty()
    inRooms = ndb.KeyProperty(kind=AERoom,repeated=True)
    '''
    def __init__(self, mateRequest):#AEMateRequest
        ndb.Model.__init__(self)
        self.username = mateRequest.username
        self.password = mateRequest.password
        self.emailAddress = mateRequest.emailAddress1
        self.description = mateRequest.description
        Construct
        '''
        
        
    def copyFromMateRequest( self,mateRequest):
        self.username = mateRequest.username
        self.password = mateRequest.password
        self.emailAddress = mateRequest.emailAddress
        self.description = mateRequest.description
        #username =messages.g
        
        

        