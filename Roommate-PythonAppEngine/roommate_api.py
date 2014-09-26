"""Hello World API implemented using Google Cloud Endpoints.

Defined here are the ProtoRPC messages needed to define Schemas for methods
as well as those methods defined in an API.
"""


import endpoints

from protorpc import messages
from protorpc import message_types
from protorpc import remote

from enity.ae_mate import AEMate
from enity.ae_mate import AEMateRequest
from enity.ae_mate import AEMateResponse
from enity.ae_room import AERoom





    
class Greeting(messages.Message):
    """Greeting that stores a message."""
    message = messages.StringField(1)
    test = messages.BooleanField(2);


class GreetingCollection(messages.Message):
    """Collection of Greetings."""
    items = messages.MessageField(Greeting, 1, repeated=True)


STORED_GREETINGS = GreetingCollection(items=[
    Greeting(message='hello world!'),
    Greeting(message='goodbye world!'),
])


@endpoints.api(name='helloworld', version='v1')
class HelloWorldApi(remote.Service):
    """Helloworld API v1."""

    @endpoints.method(AEMateRequest, AEMateResponse,
                      path='registerMate', http_method='GET',
                      name='roommate.registerMate')
    def labeeb_testing(self, request):
        #sandy =request
        #
        mate = AEMate()
        mate.copyFromMateRequest(request);
        sandy_key = mate.put()
        response = AEMateResponse()
        response.username = mate.username
        response.password = mate.password
        response.mateId = sandy_key.id()
        #msg = "key =",mate.description #,sandy_key
        return  response
    
    @endpoints.method(message_types.VoidMessage, GreetingCollection,
                      path='hellogreeting', http_method='GET',
                      name='greetings.listGreeting')
    def greetings_list(self, unused_request):
        return STORED_GREETINGS

    MULTIPLY_METHOD_RESOURCE = endpoints.ResourceContainer(
            Greeting,
            times=messages.IntegerField(2, variant=messages.Variant.INT32,
                                        required=True))

    @endpoints.method(MULTIPLY_METHOD_RESOURCE, Greeting,
                      path='hellogreeting/{times}', http_method='POST',
                      name='greetings.multiply')
    def greetings_multiply(self, request):
        return Greeting(message=request.message * request.times)

    ID_RESOURCE = endpoints.ResourceContainer(
            message_types.VoidMessage,
            id=messages.IntegerField(1, variant=messages.Variant.INT32))

    @endpoints.method(ID_RESOURCE, Greeting,
                      path='hellogreeting/{id}', http_method='GET',
                      name='greetings.getGreeting')
    def greeting_get(self, request):
        try:
            return STORED_GREETINGS.items[request.id]
        except (IndexError, TypeError):
            raise endpoints.NotFoundException('Greeting %s not found.' %
                                              (request.id,))


APPLICATION = endpoints.api_server([HelloWorldApi])