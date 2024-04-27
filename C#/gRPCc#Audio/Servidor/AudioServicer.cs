using Google.Protobuf;
using Grpc.Core;

public class AudioServicer : AudioService.AudioServiceBase
{
    private const int chunkSize = 1024;

    public override async Task downloadAudio(DownloadFileRequest request, IServerStreamWriter<DatachunkResponse> responseStream, ServerCallContext context)
    {
        var buffer = new byte[chunkSize];
        await using var fileStream = File.OpenRead($"Recursos\\{request.Nombre}");
        var numBytesRead = 0;

        Console.WriteLine($"\n\nEnviando el archivo: {request.Nombre}");
        try
        {
            while ((numBytesRead = await fileStream.ReadAsync(buffer)) > 0)
            {
                await responseStream.WriteAsync(new DatachunkResponse
                {
                    Data = UnsafeByteOperations.UnsafeWrap(buffer.AsMemory(0, numBytesRead))
                });

                Console.Write(".");
            }
        }catch
        {

        }
    }
}