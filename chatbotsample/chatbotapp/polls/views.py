from django.contrib.auth import authenticate
from django.http import JsonResponse
from django.shortcuts import render

# Create your views here.
from django.views.decorators.csrf import csrf_exempt


@csrf_exempt
def app_msg(request):
    if request.method == 'POST':
        msg=request.POST.get('msg','')
        print(msg)

        return JsonResponse({'msgg':'성공'}, status=200)
