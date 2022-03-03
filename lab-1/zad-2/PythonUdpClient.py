import socket

serverIP = "127.0.0.1"
serverPort = 9008
msg = "żółta gęś"
terminator = '#'
msg += terminator

print('PYTHON UDP CLIENT')
client = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
client.sendto(bytes(msg, 'utf-8'), (serverIP, serverPort))      # must be UTF-8 encoding
